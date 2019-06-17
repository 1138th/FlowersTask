package Utilities;

import Flowers.Flower;

import java.util.Map;

public interface Usable {

    Map create();

    void print(Map<Integer, Flower> someMap);

    void clear(Map<Integer, Flower> someMap);
}
