package com.sergio.twitter.data;

public interface Mapper<FROM, TO> {

    TO map(FROM from);

}
