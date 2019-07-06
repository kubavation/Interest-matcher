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
import org.springframework.stereotype.Service
import java.io.IOException
import java.util.*
import javax.annotation.PostConstruct

@Service
class LocationService(val locationRepository: LocationRepository) {

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

    fun findById(id: UUID) = locationRepository.findById(id)

    fun createLocation(location: Location) = locationRepository.save(location)

    //todo function to construct


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


        return Pair(latField.text.toDouble(),
                    lngField.text.toDouble())
    }

    //todo
    fun validateLocation(location: Location) {
        if(location.country.isNullOrEmpty() || location.city.isNullOrEmpty())
            throw UnsupportedOperationException("Bad init values")
    }

}