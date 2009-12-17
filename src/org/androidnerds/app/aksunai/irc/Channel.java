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
import java.util.Collections;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Channel holds a topic, an alphabetically sorted user list, and a list of messages.
 */
public class Channel extends MessageList {
    public String mTopic;
    public SortedSet<String> mUsers;

    /**
     * Class constructor.
     *
     * @param title a String, used as window title by the ChatManager
     * @param topic a String, the topic and its setter
     */
    public Channel(String title, String topic) {
        super(title);
        this.mTopic = topic;
        this.mUsers = Collections.synchronizedSortedSet(new TreeSet<String>());
    }
}

