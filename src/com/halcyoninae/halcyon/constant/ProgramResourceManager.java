/*
 *  Copyright: (C) 2022 name of Jack Meng
 * Halcyon MP4J is music-playing software.
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program; If not, see <http://www.gnu.org/licenses/>.
 */

package com.halcyoninae.halcyon.constant;

import com.halcyoninae.halcyon.connections.properties.Property;
import com.halcyoninae.halcyon.connections.properties.PropertyValidator;
import com.halcyoninae.halcyon.connections.properties.ResourceFolder;
import com.halcyoninae.halcyon.connections.properties.validators.*;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

/**
 * A constant defined class that holds
 * values for any external resources, such as
 * the properties file for the program config.
 *
 * @author Jack Meng
 * @since 3.0
 * @see com.halcyoninae.halcyon.connections.properties.ResourceFolder
 */
public class ProgramResourceManager {

  private ProgramResourceManager() {
  }

  public static final String KEY_USER_DEFAULT_FOLDER = "user.default.folder";
  public static final String KEY_USE_MEDIA_TITLE_AS_INFOVIEW_HEADER = "audio.info.media_title_as_header";
  public static final String KEY_INFOVIEW_BACKDROP_USE_GREYSCALE = "audio.info.backdrop_use_grayscale";
  public static final String KEY_INFOVIEW_BACKDROP_USE_GRADIENT = "audio.info.backdrop_use_gradient";
  public static final String KEY_PROGRAM_FORCE_OPTIMIZATION = "user.force_optimization";
  public static final String KEY_INFOVIEW_BACKDROP_GRADIENT_STYLE = "audio.info.backdrop_gradient_style";
  public static final String KEY_PROGRAM_HIDPI_VALUE = "user.hidpi_value";
  public static final String KEY_USER_DSIABLE_CLI = "user.disable_cli";
  public static final String KEY_USER_USE_DISCORD_RPC = "user.use_discord_rpc";
  public static final String KEY_USER_CHAR_SET_WRITE_TABLE = "user.charset_write_table";
  public static final String KEY_AUDIO_DEFAULT_BUFFER_SIZE = "audio.buffer_size";

  public static final Property[] propertiesList = {
      new Property(KEY_USER_DEFAULT_FOLDER, ".", new DirectoryValidator()),
      new Property(KEY_USE_MEDIA_TITLE_AS_INFOVIEW_HEADER,
          "true", new BooleanValidator()),
      new Property(KEY_INFOVIEW_BACKDROP_USE_GREYSCALE,
          "false", new BooleanValidator()),
      new Property(KEY_INFOVIEW_BACKDROP_USE_GRADIENT, "true",
          new BooleanValidator()),
      new Property(KEY_PROGRAM_FORCE_OPTIMIZATION, "true", new BooleanValidator()),
      new Property(KEY_INFOVIEW_BACKDROP_GRADIENT_STYLE,
          "focused", new StrictValidator("top", "left", "right",
              "focused")),
      new Property(KEY_PROGRAM_HIDPI_VALUE, "1.0", new NumericRangeValidator(0.9d, 1.1d, 0.1d)),
      new Property(KEY_USER_DSIABLE_CLI, "true", new BooleanValidator()),
      new Property(KEY_USER_USE_DISCORD_RPC, "true", new BooleanValidator()),
      new Property(KEY_USER_CHAR_SET_WRITE_TABLE, "utf-8", new StrictValidator("utf8", "utf16le",
          "utf16be")),
      new Property(KEY_AUDIO_DEFAULT_BUFFER_SIZE, "auto", new DefaultValidator()),
  };

  public static final String FILE_SLASH = "/";
  public static final String PROGRAM_RESOURCE_FOLDER = "halcyon";
  public static final String PROGRAM_RESOURCE_FILE_PROPERTIES = "halcyon.properties";
  public static final String[] RESOURCE_SUBFOLDERS = { "log", "bin", "user" };
  public static final String DEFAULT_ARTWORK_FILE_NAME = "artwork_cache.png";

  /**
   * @return The Map of default properties
   */
  public static Map<String, String> getProgramDefaultProperties() {
    Map<String, String> properties = new HashMap<>();
    for (Property p : propertiesList)
      properties.put(p.propertyName, p.defaultProperty);
    return properties;
  }

  /**
   * @return The map of the allowed properties
   */
  public static Map<String, PropertyValidator> getAllowedProperties() {
    Map<String, PropertyValidator> properties = new HashMap<>();
    for (Property p : propertiesList)
      properties.put(p.propertyName, p.pr);
    return properties;
  }

  /**
   * Writes a bufferedimage to the resource folder.
   *
   * @param img An image to write; a BufferedImage instance
   * @return The string representing the location of the image (ABSOLUTE PATH)
   */
  public static String writeBufferedImageToBin(BufferedImage img) {
    return ResourceFolder.writeBufferedImageCacheFile(
        img,
        RESOURCE_SUBFOLDERS[1],
        DEFAULT_ARTWORK_FILE_NAME);
  }
}
