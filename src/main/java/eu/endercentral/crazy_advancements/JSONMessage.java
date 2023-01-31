package eu.endercentral.crazy_advancements;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import net.minecraft.network.chat.IChatBaseComponent;
import net.minecraft.network.chat.IChatBaseComponent.ChatSerializer;

/**
 * Represents a Message in JSON Format
 * 
 * @author Axel
 *
 */
public class JSONMessage {
	
	private final Component json;
	
	/**
	 * Constructor for creating a JSON Message
	 * 
	 * @param json A JSON representation of an ingame Message <a href="https://www.spigotmc.org/wiki/the-chat-component-api/">Read More</a>
	 */
	public JSONMessage(Component json) {
		this.json = json;
	}
	
	/**
	 * Gets the Message as a BaseComponent
	 * 
	 * @return the BaseComponent of an ingame Message
	 */
	public Component getJson() {
		return json;
	}
	
	/**
	 * Gets an NMS representation of an ingame Message
	 * 
	 * @return An {@link IChatBaseComponent} representation of an ingame Message
	 */
	public IChatBaseComponent getBaseComponent() {
		return ChatSerializer.a(LegacyComponentSerializer.builder()
				.character('&')
				.hexCharacter('#')
				.hexColors()
				.build()
				.serialize(json));
	}
	
	@Override
	public String toString() {
		return json.toString();
	}
	
}