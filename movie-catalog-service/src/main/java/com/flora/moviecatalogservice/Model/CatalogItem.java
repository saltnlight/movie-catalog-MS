package com.flora.moviecatalogservice.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CatalogItem {
    private String name;
    private String desc;
    private int rating;
}
