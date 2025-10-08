package com.myntra.application.service;

import com.myntra.application.model.Item;
import com.myntra.application.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ItemService implements CommandLineRunner {

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public void run(String... args) throws Exception {
        // Check if items already exist
        if (itemRepository.count() == 0) {
            initializeItems();
        }
    }

    private void initializeItems() {
        List<Item> items = Arrays.asList(
            // Men's Items
            new Item("images/1.jpg", "Caratlene", "Ear Tops", 12999.0, 15999.0, 19, 4.5, 120, "Women"),
            new Item("images/2.jpg", "Fig", "Bodycon Mini Dress", 18999.0, 21999.0, 14, 4.3, 89, "Women"),
            new Item("images/3.jpg", "Kalini", "Flared Maxi Skirt", 8999.0, 11999.0, 25, 4.1, 67, "Women"),
            new Item("images/4.jpg", "Puma", "Men slim Fit T-shirt", 5999.0, 7999.0, 25, 4.4, 156, "Men"),
            new Item("images/5.jpg", "Fuaark", "Men slim Fit T-shirt", 3999.0, 4999.0, 20, 4.6, 234, "Men"),
            new Item("images/6.jpg", "Nike", "Basketball shoes", 4999.0, 6499.0, 23, 4.2, 98, "Men"),
            new Item("images/7.jpg", "Kalini", "Trousers", 7999.0, 9999.0, 20, 4.3, 112, "Men"),
            new Item("images/8.jpg", "Nivea", "Nivea men's perfume", 6999.0, 8999.0, 22, 4.0, 76, "Men"),
            new Item("images/9.jpg", "Sarai Creations", "Women's A line midi dress", 12999.0, 15999.0, 19, 4.5, 120, "Women"),
            new Item("images/10.jpg", "Aayu", "Georgette maxi Wrap Dress", 18999.0, 21999.0, 14, 4.3, 89, "Women"),
            new Item("images/11.jpg", "Metro", "Embelished Block Heels Sandal", 8999.0, 11999.0, 25, 4.1, 67, "Women"),
            new Item("images/12.jpg", "Metro", "Women Thong Flip Flop", 5999.0, 7999.0, 25, 4.4, 156, "Women"),
            new Item("images/13.jpg", "Bata", "Maroon embelished wedge sandals", 3999.0, 4999.0, 20, 4.6, 234, "Women"),
            new Item("images/14.jpg", "Rubans", "24k gold plated ruby, emerald floral chocker jwellery set", 4999.0, 6499.0, 23, 4.2, 98, "Women"),
            new Item("images/15.jpg", "Rubans", "22k gold plated necklace set", 7999.0, 9999.0, 20, 4.3, 112, "Women"),
            new Item("images/16.jpg", "Veni", "92.5 silver plated oxidised necklace set", 6999.0, 8999.0, 22, 4.0, 76, "Women"),
            new Item("images/17.jpg", "Zaveri", "Gold Plated jwellery set", 12999.0, 15999.0, 19, 4.5, 120, "Women"),
            new Item("images/18.jpg", "Hush Puppies", "Comfortable sandals", 18999.0, 21999.0, 14, 4.3, 89, "Men"),
            new Item("images/19.jpg", "Red Tape", "Memory Foam sneakers", 8999.0, 11999.0, 25, 4.1, 67, "Men"),
            new Item("images/20.jpg", "Manyvar", "Embroidered indo western sherwani set", 5999.0, 7999.0, 25, 4.4, 156, "Men"),
            new Item("images/21.jpg", "Rare rabbit", "Graphic print sweatshirt", 3999.0, 4999.0, 20, 4.6, 234, "Men"),
            new Item("images/22.jpg", "Pepe Jeans", "Plane greay t-shirt", 4999.0, 6499.0, 23, 4.2, 98, "Men"),
            new Item("images/23.jpg", "First Cry", "Kids fancy dress", 7999.0, 9999.0, 20, 4.3, 112, "Kids"),
            new Item("images/24.jpg", "Marks & Spencer", "Boy's kurta with payjama", 6999.0, 8999.0, 22, 4.0, 76, "Kids"),
            new Item("images/25.jpg", "Little Bansi", "Boy's silk kurta with payjamas", 12999.0, 15999.0, 19, 4.5, 120, "Kids"),
            new Item("images/26.jpg", "Home Town", "Single Long Greay Sofa", 18999.0, 21999.0, 14, 4.3, 89, "Home & Living"),
            new Item("images/27.jpg", "Weaving Homes", "6 Pieces Ceramic dinner set", 8999.0, 11999.0, 25, 4.1, 67, "Home & Living"),
            new Item("images/28.jpg", "Home Town", "Long Sofa set", 5999.0, 7999.0, 25, 4.4, 156, "Home & Living"),
            new Item("images/29.jpg", "Home Center", "Wodden center table", 3999.0, 4999.0, 20, 4.6, 234, "Home & Living"),
            new Item("images/30.jpg", "Maybelin", "Maybelin makeup kit", 4999.0, 6499.0, 23, 4.2, 98, "Beauty"),
            new Item("images/31.jpg", "Sugar", "Sugar Makeup Kit", 7999.0, 9999.0, 20, 4.3, 112, "Beauty"),
            new Item("images/32.jpg", "Sugar", "Sugar Makeup Kit with makeup box", 6999.0, 8999.0, 22, 4.0, 76, "Beauty"),
            new Item("images/33.jpg", "Nike", "Studio set", 6999.0, 8999.0, 22, 4.0, 76, "Studio")
            
//            // Women's Items
//            new Item("images/1.jpg", "Nike", "Air Force 1", 8999.0, 10999.0, 18, 4.4, 145, "Women"),
//            new Item("images/2.jpg", "Adidas", "Stan Smith", 6999.0, 8999.0, 22, 4.2, 98, "Women"),
//            new Item("images/3.jpg", "Puma", "Cali Sport", 5999.0, 7999.0, 25, 4.3, 87, "Women"),
//            new Item("images/4.jpg", "Reebok", "Club C 85", 4999.0, 6999.0, 29, 4.1, 123, "Women"),
//            new Item("images/5.jpg", "Converse", "Chuck 70", 5999.0, 7999.0, 25, 4.5, 167, "Women"),
//            new Item("images/6.jpg", "Vans", "Authentic", 3999.0, 5499.0, 27, 4.0, 89, "Women"),
//            new Item("images/7.jpg", "New Balance", "327", 7999.0, 9999.0, 20, 4.2, 134, "Women"),
//            new Item("images/8.jpg", "Fila", "Ray Tracer", 4999.0, 6999.0, 29, 4.1, 76, "Women"),
//            
//            // Kids Items
//            new Item("images/1.jpg", "Nike", "Air Max 90", 4999.0, 6999.0, 29, 4.3, 89, "Kids"),
//            new Item("images/2.jpg", "Adidas", "Superstar", 3999.0, 5999.0, 33, 4.4, 67, "Kids"),
//            new Item("images/3.jpg", "Puma", "Suede Classic", 2999.0, 4999.0, 40, 4.2, 45, "Kids"),
//            new Item("images/4.jpg", "Reebok", "Classic Nylon", 2499.0, 3999.0, 38, 4.1, 56, "Kids"),
//            new Item("images/5.jpg", "Converse", "Chuck Taylor", 1999.0, 2999.0, 33, 4.5, 78, "Kids"),
//            new Item("images/6.jpg", "Vans", "Slip-On", 2299.0, 3499.0, 34, 4.0, 34, "Kids"),
//            new Item("images/7.jpg", "New Balance", "574", 3499.0, 4999.0, 30, 4.3, 67, "Kids"),
//            new Item("images/8.jpg", "Fila", "Disruptor", 2799.0, 3999.0, 30, 4.1, 23, "Kids"),
//            
//            // Home & Living Items
//            new Item("images/1.jpg", "IKEA", "Modern Sofa", 25999.0, 32999.0, 21, 4.2, 45, "Home & Living"),
//            new Item("images/2.jpg", "West Elm", "Coffee Table", 15999.0, 19999.0, 20, 4.4, 67, "Home & Living"),
//            new Item("images/3.jpg", "Crate & Barrel", "Dining Chair", 8999.0, 11999.0, 25, 4.1, 89, "Home & Living"),
//            new Item("images/4.jpg", "Pottery Barn", "Bed Frame", 19999.0, 24999.0, 20, 4.3, 56, "Home & Living"),
//            new Item("images/5.jpg", "CB2", "Floor Lamp", 4999.0, 6999.0, 29, 4.0, 78, "Home & Living"),
//            new Item("images/6.jpg", "Restoration Hardware", "Throw Pillow", 2999.0, 3999.0, 25, 4.2, 34, "Home & Living"),
//            new Item("images/7.jpg", "Anthropologie", "Wall Art", 3999.0, 5999.0, 33, 4.4, 67, "Home & Living"),
//            new Item("images/8.jpg", "Urban Outfitters", "Plant Pot", 1999.0, 2999.0, 33, 4.1, 23, "Home & Living"),
//            
//            // Beauty Items
//            new Item("images/1.jpg", "MAC", "Lipstick Set", 2999.0, 3999.0, 25, 4.5, 145, "Beauty"),
//            new Item("images/2.jpg", "Sephora", "Foundation", 1999.0, 2999.0, 33, 4.3, 98, "Beauty"),
//            new Item("images/3.jpg", "Fenty Beauty", "Highlighter", 2499.0, 3499.0, 29, 4.4, 87, "Beauty"),
//            new Item("images/4.jpg", "Too Faced", "Eyeshadow Palette", 3999.0, 5999.0, 33, 4.2, 123, "Beauty"),
//            new Item("images/5.jpg", "Urban Decay", "Mascara", 1799.0, 2499.0, 28, 4.1, 167, "Beauty"),
//            new Item("images/6.jpg", "NARS", "Blush", 2299.0, 3299.0, 30, 4.3, 89, "Beauty"),
//            new Item("images/7.jpg", "Charlotte Tilbury", "Primer", 3499.0, 4999.0, 30, 4.5, 134, "Beauty"),
//            new Item("images/8.jpg", "Glossier", "Skincare Set", 4999.0, 6999.0, 29, 4.2, 76, "Beauty"),
//            
//            // Studio Items
//            new Item("images/1.jpg", "Canon", "EOS R5 Camera", 249999.0, 299999.0, 17, 4.6, 89, "Studio"),
//            new Item("images/2.jpg", "Sony", "A7 III", 199999.0, 239999.0, 17, 4.5, 67, "Studio"),
//            new Item("images/3.jpg", "Nikon", "D850", 189999.0, 229999.0, 17, 4.4, 45, "Studio"),
//            new Item("images/4.jpg", "Fujifilm", "X-T4", 159999.0, 189999.0, 16, 4.3, 56, "Studio"),
//            new Item("images/5.jpg", "Panasonic", "GH5", 129999.0, 159999.0, 19, 4.2, 78, "Studio"),
//            new Item("images/6.jpg", "Olympus", "OM-D E-M1", 99999.0, 129999.0, 23, 4.1, 34, "Studio"),
//            new Item("images/7.jpg", "Leica", "Q2", 399999.0, 449999.0, 11, 4.7, 67, "Studio"),
//            new Item("images/8.jpg", "Hasselblad", "X1D II", 599999.0, 699999.0, 14, 4.8, 23, "Studio")
        );

        itemRepository.saveAll(items);
        System.out.println("Initialized " + items.size() + " items in the database");
    }
}
