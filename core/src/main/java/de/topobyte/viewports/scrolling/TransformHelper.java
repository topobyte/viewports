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

package de.topobyte.viewports.scrolling;

import de.topobyte.lina.AffineTransformUtil;
import de.topobyte.lina.Matrix;
import de.topobyte.viewports.geometry.Rectangle;

public class TransformHelper
{

	public static Matrix createMatrix(Rectangle scene, Viewport viewport)
	{
		Matrix shift = AffineTransformUtil.translate(-scene.getX1(),
				-scene.getY1());
		Matrix translate = AffineTransformUtil.translate(
				viewport.getPositionX(), viewport.getPositionY());
		Matrix scale = AffineTransformUtil.scale(viewport.getZoom(),
				viewport.getZoom());

		Matrix matrix = scale.multiplyFromRight(translate).multiplyFromRight(
				shift);
		return matrix;
	}

	public static Matrix createInverseMatrix(Rectangle scene, Viewport viewport)
	{
		return createMatrix(scene, viewport).invert();
	}

}
