/*
 * This file is part of SkyChanger, licensed under the MIT License (MIT).
 *
 * Copyright (c) 2017-2020 Daniel D. Scalzi <https://github.com/dscalzi/SkyChanger>
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

package com.dscalzi.skychanger.sponge.internal.wrap;

import com.dscalzi.skychanger.core.internal.wrap.ICommandSender;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.source.CommandBlockSource;
import org.spongepowered.api.command.source.ConsoleSource;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.serializer.TextSerializers;

public class SpongeCommandSender extends SpongePermissible implements ICommandSender {

    private final CommandSource cs;

    protected SpongeCommandSender(CommandSource commandSource) {
        super(commandSource);
        this.cs = commandSource;
    }

    public static SpongeCommandSender of(CommandSource commandSource) {
        if(commandSource == null) {
            return null;
        }
        if(commandSource instanceof Player) {
            return SpongePlayer.of((Player)commandSource);
        } else if(commandSource instanceof CommandBlockSource) {
            return SpongeCommandBlock.of((CommandBlockSource)commandSource);
        } else {
            return new SpongeCommandSender(commandSource);
        }
    }

    @Override
    public boolean isConsole() {
        return cs instanceof ConsoleSource;
    }

    @Override
    public boolean isCommandBlock() {
        return cs instanceof CommandBlockSource;
    }

    @Override
    public boolean isPlayer() {
        return cs instanceof Player;
    }

    @Override
    public void sendMessage(String msg) {
        cs.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(msg));
    }
}
