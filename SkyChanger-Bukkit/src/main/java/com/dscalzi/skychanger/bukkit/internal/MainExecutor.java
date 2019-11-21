/*
 * This file is part of SkyChanger, licensed under the MIT License (MIT).
 *
 * Copyright (c) 2017-2019 Daniel D. Scalzi <https://github.com/dscalzi/SkyChanger>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.dscalzi.skychanger.bukkit.internal;

import java.util.List;

import com.dscalzi.skychanger.bukkit.internal.wrap.BukkitCommandSender;
import com.dscalzi.skychanger.core.internal.command.CommandAdapter;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import com.dscalzi.skychanger.bukkit.SkyChangerPlugin;

public class MainExecutor implements CommandExecutor, TabCompleter {

    private CommandAdapter adapter;

    public MainExecutor(SkyChangerPlugin plugin) {
        this.adapter = new CommandAdapter(plugin);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        return this.adapter.resolve(BukkitCommandSender.of(sender), args);
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        return adapter.tabComplete(BukkitCommandSender.of(sender), args);
    }

}
