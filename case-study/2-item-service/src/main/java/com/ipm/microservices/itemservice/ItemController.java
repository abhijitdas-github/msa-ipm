package com.ipm.microservices.itemservice;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RefreshScope // Add this class in scope for property refresh
@RestController
public class ItemController {
	private static Logger log = LoggerFactory.getLogger(ItemController.class);

	@Value("${itemName:PNFitemName}")
	private String itemName;

	@Value("${itemDescription:PNFitemDescription}")
	private String description;

	@Autowired
	Environment environment;

	@Autowired
	ItemRepository itemRepo;

	@RequestMapping(value = "/")
	public String home() {
		log.info("item-service is up and running!");
		return "item-service is up and running!";
	}

	@RequestMapping(value = "/items", method = RequestMethod.GET)
	public List<Item> getItems() {
		log.info("Getting all items from Database");
		List<Item> itemList = itemRepo.findAll();
		int idx = 0;
		for (Item item : itemList) {
			log.info("Found Item = [" + ++idx + ": " + Utility.objToStr(item) + "]");
		}
		return itemList;
	}

	@RequestMapping(value = "/item/{itemName}", method = RequestMethod.GET)
	@HystrixCommand(fallbackMethod = "getItem_Fallback")
	public Item getItem(@PathVariable String itemName) {
		log.info("PORT:" + environment.getProperty("local.server.port"));
		log.info("Getting Item with [name=" + itemName + "] from database");
		Item item = itemRepo.findByItemName(itemName);
		if (item != null)
			log.info("Item found ... " + Utility.objToStr(item));
		else
			throw new NullPointerException();
		return item;
	}

	public Item getItem_Fallback(String itemName, Throwable ex) {
		log.info("Item not found, Executing fallback method..");
		Item item = new Item();
		item.setItemName(this.itemName);
		item.setDescription(description);
		log.info("Error getting item ... " + Utility.objToStr(item));
		return item;
	}
}
