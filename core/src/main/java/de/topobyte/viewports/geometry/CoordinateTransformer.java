// Copyright 2017 Sebastian Kuerten
//
// This file is part of viewports.
//
// viewports is free software: you can redistribute it and/or modify
// it under the terms of the GNU Lesser General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.
//
// viewports is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
// GNU Lesser General Public License for more details.
//
// You should have received a copy of the GNU Lesser General Public License
// along with viewports. If not, see <http://www.gnu.org/licenses/>.

package de.topobyte.viewports.geometry;

import de.topobyte.lina.Matrix;
import de.topobyte.lina.Vector;
import de.topobyte.lina.VectorType;

public class CoordinateTransformer
{

	protected Matrix matrix;

	public CoordinateTransformer(Matrix matrix)
	{
		this.matrix = matrix;
	}

	public Coordinate transform(Coordinate c)
	{
		Vector v = new Vector(3, VectorType.Column);
		v.setValue(0, c.getX());
		v.setValue(1, c.getY());
		v.setValue(2, 1);
		Matrix r = matrix.multiplyFromRight(v);
		double x = r.getValue(0, 0);
		double y = r.getValue(0, 1);
		return new Coordinate(x, y);
	}

}
