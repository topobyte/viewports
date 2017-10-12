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

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import de.topobyte.viewports.geometry.Coordinate;
import de.topobyte.viewports.scrolling.Viewport;

public class ViewportMouseListener extends MouseAdapter
{

	private Viewport viewport;

	public ViewportMouseListener(Viewport viewport)
	{
		this.viewport = viewport;
	}

	protected double getX(MouseEvent e)
	{
		return e.getX() / viewport.getZoom() - viewport.getPositionX();
	}

	protected double getY(MouseEvent e)
	{
		return e.getY() / viewport.getZoom() - viewport.getPositionY();
	}

	protected Coordinate getCoordinate(MouseEvent e)
	{
		double posX = viewport.getPositionX();
		double posY = viewport.getPositionY();
		return new Coordinate(e.getX() / viewport.getZoom() - posX,
				e.getY() / viewport.getZoom() - posY);
	}

}
