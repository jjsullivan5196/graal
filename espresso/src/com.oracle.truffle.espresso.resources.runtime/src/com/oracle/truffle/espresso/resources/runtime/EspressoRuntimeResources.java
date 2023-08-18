/*
 * Copyright (c) 2023, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */
package com.oracle.truffle.espresso.resources.runtime;

import java.io.IOException;
import java.nio.file.Path;

import com.oracle.truffle.api.CompilerDirectives;
import com.oracle.truffle.api.InternalResource;

@InternalResource.Id(value = "espresso-runtime", componentId = "java", optional = true)
public final class EspressoRuntimeResources implements InternalResource {

    private static Path basePath(Env env) {
        return Path.of("META-INF", "resources", "java", "espresso-runtime", env.getOS().toString(), env.getCPUArchitecture().toString());
    }

    @Override
    public void unpackFiles(Env env, Path targetDirectory) throws IOException {
        Path base = basePath(env);
        env.unpackResourceFiles(base.resolve("files"), targetDirectory, base);
    }

    @Override
    public String versionHash(Env env) {
        try {
            Path hashResource = basePath(env).resolve("sha256");
            return env.readResourceLines(hashResource).get(0);
        } catch (IOException ioe) {
            throw CompilerDirectives.shouldNotReachHere(ioe);
        }
    }
}
