/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.commons.math3.ode;

import org.apache.commons.math3.RealFieldElement;
import org.apache.commons.math3.util.MathArrays;

/**
 * Class mapping the part of a complete state or derivative that pertains
 * to a specific differential equation.
 * <p>
 * Instances of this class are guaranteed to be immutable.
 * </p>
 * @see FieldSecondaryEquations
 * @param <T> the type of the field elements
 * @since 3.6
 */
public class FieldEquationsMapper<T extends RealFieldElement<T>> {

    /** Index of the first equation element in complete state arrays. */
    private final int firstIndex;

    /** Dimension of the secondary state parameters. */
    private final int dimension;

    /** simple constructor.
     * @param firstIndex index of the first equation element in complete state arrays
     * @param dimension dimension of the secondary state parameters
     */
    FieldEquationsMapper(final int firstIndex, final int dimension) {
        this.firstIndex = firstIndex;
        this.dimension  = dimension;
    }

    /** Get the index of the first equation element in complete state arrays.
     * @return index of the first equation element in complete state arrays
     */
    public int getFirstIndex() {
        return firstIndex;
    }

    /** Get the dimension of the secondary state parameters.
     * @return dimension of the secondary state parameters
     */
    public int getDimension() {
        return dimension;
    }

    /** Extract equation data from a complete state or derivative array.
     * @param complete complete state or derivative array from which
     * equation data should be retrieved
     * @return equation data
     */
    public T[] extractEquationData(T[] complete) {
        final T[] equationData = MathArrays.buildArray(complete[0].getField(), dimension);
        System.arraycopy(complete, firstIndex, equationData, 0, dimension);
        return equationData;
    }

    /** Insert equation data into a complete state or derivative array.
     * @param equationData equation data to be inserted into the complete array
     * @param complete placeholder where to put equation data (only the
     * part corresponding to the equation will be overwritten)
     */
    public void insertEquationData(T[] equationData, T[] complete) {
        System.arraycopy(equationData, 0, complete, firstIndex, dimension);
    }

}