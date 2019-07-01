package io.duryskuba.interestmatcher.TagService.utils;

import io.duryskuba.interestmatcher.TagService.resource.Tag;
import org.javatuples.Pair;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RefreshScope
public class TagContentBuilder {

    @Value("${edge-service.prefix}")
    private String prefix;
    @Value("${spring.application.name}")
    private String appName;


    public Pair<String, List<Tag>> pullOutTags(String content) {
        StringBuilder resultContent = new StringBuilder();
        content += " ";
        List<Tag> result = new ArrayList<>();
        while (true) {

            int index = content.indexOf('#');

            int temp = index;
            while(content.charAt(++temp) == '#')
                index = temp;

            if (index == -1) break;

            resultContent.append(content.substring(0,index));
            String restOfContent = content.substring(index + 1);

            int endIndex = calculateEndIndex(restOfContent.indexOf(" "),restOfContent.indexOf("#"));
            if(endIndex == -1) break;

            String newContent = restOfContent.substring(endIndex);
            String tagStr = restOfContent.substring(0, endIndex);

            if(isTagValid(tagStr)) {
                resultContent.append(buildTagLink(tagStr));
                Tag newTag = new Tag();
                newTag.setName(tagStr);
                result.add(newTag);
            }

            content = newContent;
        }

        return new Pair<>(resultContent.toString(), result);
    }


    private String buildTagLink(String tagStr) {
//        return String
//                .format("<a href='localhost:%1$s/posts/tags/%2$s'>#%2$s</a>", port, tagStr);
        return String
                .format("<a href='http://%1$s/posts/tags/%2$s'>#%2$s</a>", appName, tagStr);
    }

    private int calculateEndIndex(int spaceIndex, int hashIndex) {
        int endIndex = -1;
        if (spaceIndex >= 0 && hashIndex <= 0) {
            endIndex = spaceIndex;
        } else if (hashIndex >= 0 && spaceIndex <= 0) {
            endIndex = hashIndex;
        } else if (hashIndex >= 0 && spaceIndex >= 0) {
            endIndex = hashIndex > spaceIndex ? spaceIndex : hashIndex;
        }
        return endIndex;
    }

    private boolean isTagValid(String tagStr) {
        return !tagStr.contains(" ") && !"".equals(tagStr);
    }

}
