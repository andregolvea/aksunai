/*
 * Copyright (C) 2009  AndroidNerds.org
 * 
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.

 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package org.androidnerds.app.aksunai.irc;

/**
 * Command is the enum used by {@link org.androidnerds.app.aksunai.irc.Message}.
 * <p>
 * It's used in switches, to style messages in windows, 
 * and the {@link org.androidnerds.app.aksunai.irc.Command#toString}
 * method is used to compare to the actual command received from the irc server.
 */
public enum Command {
    /* server initiated messages, no command, numeric */
    CONNECTED ("001"),              // :server 001 nick :Welcome to the freenode IRC Network OhanTest
    NONICK ("431"),                 // :server 431 :No nickname given
    ERRONEUSNICK ("432"),           // :server 432 * nick :Erroneous Nickname
    NICKINUSE ("433"),              // :server 433 * nick :Nickname is already in use
    NICKCOLLISION ("434"),          // :server 434 ???
    OTHER (""),

    /* user queries */
    NICK ("NICK"),                  // :nick!n=user@host NICK :newnick
    QUIT ("QUIT"),                  // :nick!n=user@host QUIT :reason

    /* channel operations */
    JOIN ("JOIN"),                  // :nick!n=user@host JOIN :#channel
    PART ("PART"),                  // :nick!n=user@host PART #channel :reason
    MODE ("MODE"),                  //
    TOPIC ("TOPIC"),                // :nick!n=user@host TOPIC #channel :topic
    NAMES ("NAMES"),                //
    LIST ("LIST"),                  //
    INVITE ("INVITE"),              // :nick!n=user@host INVITE othernick :#channel
    KICK ("KICK"),                  // :nick!n=user@host KICK #channel othernick :reason

    /* sending messages */
    PRIVMSG ("PRIVMSG"),            // :nick!n=user@host PRIVMSG #channel :test
    NOTICE ("NOTICE"),              // :nick!n=user@host NOTICE othernick :test

    /* miscellaneous messages */
    PING ("PING"),                  // PING :server

    /* unknown command */
    UNKNOWN ("UNKNOWN");


    private final String mStr;

    /**
     * Enum constructor. Sets the internal string representation of each command.
     */
    Command(String string) {
        this.mStr = string;
    }

    /**
     * Returns true if the given string is equal to the internal representation of this command.
     * The check is case insensitive.
     *
     * @param command a string to be compared to
     * @return true if the parameter is equal to the string representation, case insensitively
     */
    public boolean equalsIgnoreCase(String command) {
        return this.mStr.equals(command.toUpperCase());
    }

    /**
     * Returns true if the internal representation of this command starts with the given string.
     * The check is case insensitive.
     *
     * @param command a string to be compared to
     * @return true if the internal representation starts with the parameter, case insensitively
     */
    public boolean startsWithIgnoreCase(String command) {
        return this.mStr.startsWith(command.toUpperCase());
    }

    /**
     * Returns the string representation of this command.
     *
     * @return the string representation of this command
     */
    public String toString() {
        return this.mStr;
    }
}

