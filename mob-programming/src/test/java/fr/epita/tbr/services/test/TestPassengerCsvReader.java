package fr.epita.tbr.services.test;

import fr.epita.tbr.datamodel.Passenger;
import fr.epita.tbr.services.PassengersCsvReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class TestPassengerCsvReader {


    @Test
    public void testReadAll() throws Exception{
        //given
        PassengersCsvReader passengersCsvReader = new PassengersCsvReader();

        Exception caughtException = null;
        //when
        try {
            List<Passenger> passengers = passengersCsvReader.readAll();
            Predicate<Passenger> passengerPredicate = p -> p.getAge() > 18;
            List<Passenger> majorPassengers = passengers.stream()
                    .filter(passengerPredicate)
                    .collect(Collectors.toList());
        }catch (Exception e){
            caughtException = e;
        }

        //then
        //...
        Assertions.assertTrue(caughtException != null);

    }

}
