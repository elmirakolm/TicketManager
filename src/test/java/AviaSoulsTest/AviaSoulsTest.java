package AviaSoulsTest;

import static org.junit.jupiter.api.Assertions.*;

import TicketManager.AviaSouls;
import TicketManager.Ticket;
import TicketManager.TicketTimeComparator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

public class AviaSoulsTest {


    @Test
    public void testSearch() {
        AviaSouls manager = new AviaSouls();
        Ticket ticket1 = new Ticket("Moscow", "London", 200, 1200, 1500);
        Ticket ticket2 = new Ticket("Moscow", "Paris", 150, 1100, 1400);
        manager.add(ticket1);
        manager.add(ticket2);

        Ticket[] result = manager.search("Moscow", "London");
        assertEquals(1, result.length);
        assertEquals(ticket1, result[0]);
    }

    @Test
    public void testSortFewTicket() {
        AviaSouls manager = new AviaSouls();

        Ticket ticket1 = new Ticket("Moscow", "London", 200, 1200, 1500);
        Ticket ticket2 = new Ticket("Paris", "Berlin", 150, 1100, 1400);
        Ticket ticket3 = new Ticket("New York", "Tokyo", 300, 1000, 1300);
        Ticket ticket4 = new Ticket("Moscow", "London", 250, 1300, 1600);
        Ticket ticket5 = new Ticket("Moscow", "London", 180, 1400, 1700);
        Ticket ticket6 = new Ticket("Singapore", "Hong Kong", 220, 1500, 1800);
        Ticket ticket7 = new Ticket("Moscow", "London", 280, 1600, 1900);
        Ticket ticket8 = new Ticket("Bangkok", "Kuala Lumpur", 190, 1700, 2000);


        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);
        manager.add(ticket4);
        manager.add(ticket5);
        manager.add(ticket6);
        manager.add(ticket7);
        manager.add(ticket8);

        Ticket[] actual = manager.search("Moscow", "London");
        Ticket[] expected = {ticket5, ticket1, ticket4, ticket7};
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void testSearchNoTickets() {
        AviaSouls manager = new AviaSouls();

        Ticket ticket1 = new Ticket("Moscow", "London", 200, 1200, 1500);
        Ticket ticket2 = new Ticket("Paris", "Berlin", 150, 1100, 1400);
        Ticket ticket3 = new Ticket("New York", "Tokyo", 300, 1000, 1300);

        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);

        Ticket[] actual = manager.search("Moscow", "Istanbul");
        Ticket[] expected = {};
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void testSearchOneTicket() {
        AviaSouls manager = new AviaSouls();

        Ticket ticket1 = new Ticket("Moscow", "London", 200, 1200, 1500);
        Ticket ticket2 = new Ticket("Paris", "Berlin", 150, 1100, 1400);
        Ticket ticket3 = new Ticket("New York", "Tokyo", 300, 1000, 1300);
        Ticket ticket4 = new Ticket("Moscow", "London", 250, 1300, 1600);
        Ticket ticket5 = new Ticket("Moscow", "London", 180, 1400, 1700);
        Ticket ticket6 = new Ticket("Singapore", "Hong Kong", 220, 1500, 1800);
        Ticket ticket7 = new Ticket("Moscow", "London", 280, 1600, 1900);
        Ticket ticket8 = new Ticket("Bangkok", "Kuala Lumpur", 190, 1700, 2000);

        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);
        manager.add(ticket4);
        manager.add(ticket5);
        manager.add(ticket6);
        manager.add(ticket7);
        manager.add(ticket8);

        Ticket[] actual = manager.search("New York", "Tokyo");
        Ticket[] expected = {ticket3};
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void testSortTicketWithComparator() {
        AviaSouls manager = new AviaSouls();

        Ticket ticket1 = new Ticket("Moscow", "London", 200, 1400, 1500); //1
        Ticket ticket2 = new Ticket("Paris", "Berlin", 150, 1100, 1400);
        Ticket ticket3 = new Ticket("New York", "Tokyo", 300, 1000, 1300);
        Ticket ticket4 = new Ticket("Moscow", "London", 250, 1400, 1600); //2
        Ticket ticket5 = new Ticket("Moscow", "London", 180, 1400, 1700); //3
        Ticket ticket6 = new Ticket("Singapore", "Hong Kong", 220, 1500, 1800);
        Ticket ticket7 = new Ticket("Moscow", "London", 280, 1500, 1900); //4
        Ticket ticket8 = new Ticket("Bangkok", "Kuala Lumpur", 190, 1700, 2000);


        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);
        manager.add(ticket4);
        manager.add(ticket5);
        manager.add(ticket6);
        manager.add(ticket7);
        manager.add(ticket8);
        Comparator<Ticket> comparator = new TicketTimeComparator();

        Ticket[] actual = manager.search("Moscow", "London", comparator);
        Ticket[] expected = {ticket1, ticket4, ticket5, ticket7};
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void testSearchOneTicketWithComparator() {
        AviaSouls manager = new AviaSouls();

        Ticket ticket1 = new Ticket("Moscow", "London", 200, 1200, 1500);
        Ticket ticket2 = new Ticket("Paris", "Berlin", 150, 1100, 1400);
        Ticket ticket3 = new Ticket("New York", "Tokyo", 300, 1000, 1300);
        Ticket ticket4 = new Ticket("Moscow", "London", 250, 1300, 1600);
        Ticket ticket5 = new Ticket("Moscow", "London", 180, 1400, 1700);
        Ticket ticket6 = new Ticket("Singapore", "Hong Kong", 220, 1500, 1800);
        Ticket ticket7 = new Ticket("Moscow", "London", 280, 1600, 1900);
        Ticket ticket8 = new Ticket("Bangkok", "Kuala Lumpur", 190, 1700, 2000);

        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);
        manager.add(ticket4);
        manager.add(ticket5);
        manager.add(ticket6);
        manager.add(ticket7);
        manager.add(ticket8);

        Comparator<Ticket> comparator = new TicketTimeComparator();
        Ticket[] actual = manager.search("New York", "Tokyo", comparator);
        Ticket[] expected = {ticket3};
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void testSearchNoTicketsWithComparator() {
        AviaSouls manager = new AviaSouls();

        Ticket ticket1 = new Ticket("Moscow", "London", 200, 1200, 1500);
        Ticket ticket2 = new Ticket("Paris", "Berlin", 150, 1100, 1400);
        Ticket ticket3 = new Ticket("New York", "Tokyo", 300, 1000, 1300);

        manager.add(ticket1);
        manager.add(ticket2);
        manager.add(ticket3);

        Comparator<Ticket> comparator = new TicketTimeComparator();

        Ticket[] actual = manager.search("Moscow", "Istanbul", comparator);
        Ticket[] expected = {};
        Assertions.assertArrayEquals(expected, actual);
    }
}