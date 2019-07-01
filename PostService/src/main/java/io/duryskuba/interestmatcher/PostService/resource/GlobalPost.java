package io.duryskuba.interestmatcher.PostService.resource;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@RequiredArgsConstructor
@Data
public class GlobalPost extends Post {
}
