/*
 * Copyright (c) 2023, 2023, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
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
package com.oracle.svm.graal.meta;

import org.graalvm.nativeimage.Platform;
import org.graalvm.nativeimage.Platforms;

import com.oracle.graal.pointsto.meta.AnalysisField;
import com.oracle.graal.pointsto.meta.AnalysisMethod;
import com.oracle.svm.core.util.HostedStringDeduplication;

/**
 * Factory for creating method and field objects that are used for JIT compilation at image run
 * time. This allows Truffle to use subclasses with more data fields.
 *
 * All methods and fields are created at image build time. The factory does not cover types, because
 * {@link SubstrateType} instances can also be created at image run time.
 */
@Platforms(Platform.HOSTED_ONLY.class)
public class SubstrateUniverseFactory {

    public SubstrateMethod createMethod(AnalysisMethod aMethod, HostedStringDeduplication stringTable) {
        return new SubstrateMethod(aMethod, stringTable);
    }

    public SubstrateField createField(AnalysisField aField, HostedStringDeduplication stringTable) {
        return new SubstrateField(aField, stringTable);
    }
}
