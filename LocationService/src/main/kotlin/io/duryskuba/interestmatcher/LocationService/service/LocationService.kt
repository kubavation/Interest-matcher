package io.duryskuba.interestmatcher.LocationService.service

import com.gargoylesoftware.htmlunit.BrowserVersion
import com.gargoylesoftware.htmlunit.Page
import com.gargoylesoftware.htmlunit.WebClient
import com.gargoylesoftware.htmlunit.html.HtmlButtonInput
import com.gargoylesoftware.htmlunit.html.HtmlForm
import com.gargoylesoftware.htmlunit.html.HtmlPage
import com.gargoylesoftware.htmlunit.html.HtmlTextInput
import io.duryskuba.interestmatcher.LocationService.repository.LocationRepository
import io.duryskuba.interestmatcher.LocationService.resource.Location
import io.duryskuba.interestmatcher.LocationService.resource.LocationDTO
import io.duryskuba.interestmatcher.LocationService.util.LocationConverter
import org.springframework.stereotype.Service
import java.io.IOException
import java.lang.RuntimeException
import java.util.*
import javax.annotation.PostConstruct

@Service
class LocationService(val locationRepository: LocationRepository,
                      val locationConverter: LocationConverter) {

    lateinit var client: WebClient
    lateinit var page: HtmlPage

    @PostConstruct
    fun init() {
        client = WebClient(BrowserVersion.CHROME)
        client.options.isCssEnabled = false

        try {
            page = client.getPage("http://gpx-poi.com")
        } catch (e: IOException) {
            throw IllegalArgumentException("Wrong url")
        }
    }

    fun findById(id: UUID): LocationDTO {
        val location = locationRepository.findById(id)
                .orElseThrow { RuntimeException("todo resourcenotfound") }
        return locationConverter.toDto(location)
    }

    fun saveLocation(location: Location) = locationRepository.save(location)


    fun createLocation(location: LocationDTO): LocationDTO {
       val locWithCoords = setCoordsOfPlace(location)
       locWithCoords.locationId = UUID.randomUUID().toString()

       val result = saveLocation(locationConverter.toEntity(locWithCoords))
       val _test_ =  locationConverter.toDto(result)
        println(_test_)
        return _test_
    }

    fun findCoordsOfPlace(location: LocationDTO): Pair<Double, Double> {

        val form = page.getHtmlElementById<HtmlForm>("gpx")
        val cityField = form.getInputByName<HtmlTextInput>("city")
        val countryField = form.getInputByName<HtmlTextInput>("country")
        val straddrField = form.getInputByName<HtmlTextInput>("staddr")

        cityField.text = location.city
        countryField.text = location.country
        straddrField.text = location.street

        val input = form.getInputByValue<HtmlButtonInput>("Go!")

        try {
            input.click<Page>()
            client.waitForBackgroundJavaScript(900)
        } catch (e: IOException) {
            throw IllegalArgumentException("coords failed")
        }

        val lngField = form.getInputByName<HtmlTextInput>("lng")
        val latField = form.getInputByName<HtmlTextInput>("lat")

        println(lngField.text)
        println(latField.text)

        return Pair(latField.text.toDouble(), lngField.text.toDouble())
    }


    fun setCoordsOfPlace(locationDTO: LocationDTO): LocationDTO {
        validateLocation(locationDTO)

        val (lat, lan) = findCoordsOfPlace(locationDTO)
        val loc = LocationDTO(locationDTO)
        loc.lan = lan
        loc.lat = lat

        return loc
    }



    fun validateLocation(location: LocationDTO) {
        if(location.country.isNullOrEmpty() || location.city.isNullOrEmpty())
            throw UnsupportedOperationException("Bad init values")
    }

}