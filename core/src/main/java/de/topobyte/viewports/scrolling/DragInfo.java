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

import de.topobyte.viewports.geometry.Coordinate;

public class DragInfo
{

	private Coordinate start;
	private Coordinate last;
	private Coordinate current;

	public DragInfo(double x, double y)
	{
		start = new Coordinate(x, y);
		last = start;
		current = start;
	}

	public void update(double x, double y)
	{
		last = current;
		current = new Coordinate(x, y);
	}

	public Coordinate getDeltaToLast()
	{
		return getDeltaTo(last);
	}

	public Coordinate getDeltaToStart()
	{
		return getDeltaTo(start);
	}

	private Coordinate getDeltaTo(Coordinate other)
	{
		double dx = current.getX() - other.getX();
		double dy = current.getY() - other.getY();
		return new Coordinate(dx, dy);
	}

}
