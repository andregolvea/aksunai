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

import android.util.Log;
import java.util.Date;

/**
 * Message is the basis of the IRC stack. It parses a message from the server (a line) into:
 * <ul>
 *     <li>sender: may be empty if it's a server message</li>
 *     <li>command: a {@link org.androidnerds.app.aksunai.irc.Command}</li>
 *     <li>parameters: the receiver, the mode, the channel...</li>
 *     <li>text: text message that appears after a ":", usually a the content of a privmsg, notice, part message, topic...</li>
 * </ul>
 *
 * Messages are instanciated by a {@link org.androidnerds.app.aksunai.Server}.
 */
public class Message {
    public String mSender;
    public Command mCommand;
    public String[] mParameters;
    public String mText;
    public long mTimestamp;

    /**
     * Class constructor. Parses a raw server message.
     */
    public Message(String line) {
        this.mTimestamp = new Date().getTime();

        int textPos = line.indexOf(" :"); /* is there a text message? */
        if (textPos != -1) {
            this.mText = line.substring(textPos + 2);
            line = line.substring(0, textPos);
        }

        if (!line.startsWith(":")) { /* sent by the server we're connected to: <command> [parameters]*/
            String[] parts = line.split(" ", 2);

            this.mCommand = getCommand(parts[0]);
            if (parts.length == 2) {
                this.mParameters = parts[1].split(" ");
            }
        } else {
            line = line.substring(1); /* strip the first ":" */
            String[] parts = line.split(" ", 3);
            
            this.mSender = parts[0];
            if (parts.length >= 2) {
                this.mCommand = getCommand(parts[1]);
            }
            if (parts.length > 2) {
                this.mParameters = parts[2].split(" ");
            }
        }
    }

    /**
     * returns the command. If the command is numeric, it's a server initiated message, and the command
     * will be NONE.
     *
     * @param line the string to get the command from
     * @return the command
     */
    public static Command getCommand(String str) {
        try {
            Integer.parseInt(str); /* if it's a numeric command, it's a server message */
            return Command.NONE;
        } catch (NumberFormatException e) {}

        for (Command c: Command.values()) {
            if (c.equalsIgnoreCase(str)) {
                return c;
            }
        }

        return Command.UNKNOWN;
    }

    /**
     * returns the string representation of this message.
     *
     * @return the string representation of this message.
     */
    public String toString() {
        String parameters = "";
        for (String param: mParameters) {
            parameters += param + " ";
        }
        parameters = parameters.trim();
        return "timestamp=" + mTimestamp + " sender=" + mSender + " command=" + mCommand + " parameters=[" + parameters + "] text=" + mText;
    }
}

