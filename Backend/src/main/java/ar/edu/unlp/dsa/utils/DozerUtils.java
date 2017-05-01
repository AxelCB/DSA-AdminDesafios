package ar.edu.unlp.dsa.utils;

/**
 * Created by acollard on 22/3/17.
 */
import java.util.ArrayList;
import java.util.List;

import org.dozer.Mapper;

/**
 * Dozer Utils and Helper methods.
 *
 * @author borrowed from fgonzalez
 *
 */
public class DozerUtils {

    /**
     * Maps an ArrayList of objects to an ArrayList of other type objects.
     *
     * @param mapper
     *            the dozer mapper
     * @param source
     *            the list source
     * @param destinationType
     *            the type for the destination list
     *
     * @return a list containing all elements of the source mapped to the
     *         destinationType
     */
    public static <T, U> ArrayList<U> map(Mapper mapper, List<T> source,
                                          Class<U> destinationType) {

        // creates the destination list
        ArrayList<U> destinationList = new ArrayList<>();

        // map every element in the source and add it to the list
        for (T element : source) {
            destinationList.add(mapper.map(element, destinationType));
        }

        return destinationList;
    }

    /**
     * Maps an ArrayList of objects to an ArrayList of other type objects using
     * the mappingId.
     *
     * @param mapper
     *            the dozer mapper
     * @param source
     *            the list source
     * @param destinationType
     *            the type for the destination list
     * @param mappingId
     *            mappingId to use with dozer
     *
     * @return a list containing all elements of the source mapped to the
     *         destinationType
     */
    public static <T, U> ArrayList<U> map(Mapper mapper, List<T> source,
                                          Class<U> destinationType, String mappingId) {

        // creates the destination list
        ArrayList<U> destinationList = new ArrayList<>();

        // map every element in the source and add it to the list
        for (T element : source) {
            if (mappingId == null) {
                destinationList.add(mapper.map(element, destinationType));
            } else {
                destinationList.add(mapper.map(element, destinationType,
                        mappingId));
            }
        }

        return destinationList;
    }
}