package eu.endercentral.crazy_advancements.advancement.serialized.message;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.SelectorComponent;
import net.kyori.adventure.text.TextComponent;;

import java.awt.*;
import java.util.List;
import java.util.Locale;

public class SerializedMessage {
	
	private final String text;
	private final String selector;
	private final String keybind;
	private final String color;
	private final boolean bold;
	private final boolean italic;
	private final boolean underlined;
	private final HoverEvent hoverEvent;
	private final ClickEvent clickEvent;
	
	private List<SerializedMessage> extra;
	
	public SerializedMessage(String text, String selector, String keybind, String color, boolean bold, boolean italic, boolean underlined, HoverEvent hoverEvent, ClickEvent clickEvent, List<SerializedMessage> extra) {
		this.text = text;
		this.selector = selector;
		this.keybind = keybind;
		this.color = color;
		this.bold = bold;
		this.italic = italic;
		this.underlined = underlined;
		this.hoverEvent = hoverEvent;
		this.clickEvent = clickEvent;
		this.extra = extra;
	}
	
	public String getText() {
		return text;
	}
	
	public String getSelector() {
		return selector;
	}
	
	public String getKeybind() {
		return keybind;
	}
	
	public String getColor() {
		return color;
	}
	
	public boolean isBold() {
		return bold;
	}
	
	public boolean isItalic() {
		return italic;
	}
	
	public boolean isUnderlined() {
		return underlined;
	}
	
	public HoverEvent getHoverEvent() {
		return hoverEvent;
	}
	
	public ClickEvent getClickEvent() {
		return clickEvent;
	}
	
	public List<SerializedMessage> getExtra() {
		return extra;
	}
	
	public Component deserialize() {
		Component message = Component.text("");
		if(getText() != null && !getText().isEmpty()) {
			message = Component.text(getText());
		} else if(getSelector() != null && !getSelector().isEmpty()) {
			message = Component.selector(getSelector());
		} else if(getKeybind() != null && !getKeybind().isEmpty()) {
			message = Component.keybind(getKeybind());
		}
		
		if(getHoverEvent() != null) {
			message.hoverEvent(
					net.kyori.adventure.text.event.HoverEvent.hoverEvent(
							net.kyori.adventure.text.event.HoverEvent.Action.SHOW_TEXT,
							Component.text(getHoverEvent().getContents())
					)
			);
		}
		
		if(getClickEvent() != null) {
			message.clickEvent(net.kyori.adventure.text.event.ClickEvent.clickEvent(
					net.kyori.adventure.text.event.ClickEvent.Action.valueOf(getClickEvent().getAction().toUpperCase()),
					getClickEvent().getValue()
			));
		}
		
		if(getExtra() != null) {
			for(SerializedMessage extra : getExtra()) {
				message.append(extra.deserialize());
			}
		}
		
		return message;
	}
	
}