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

public class ViewportUtil
{

	public static double getRealX(Viewport viewport, double x)
	{
		return x / viewport.getZoom() - viewport.getPositionX();
	}

	public static double getRealY(Viewport viewport, double y)
	{
		return y / viewport.getZoom() - viewport.getPositionY();
	}

	public static double getViewX(Viewport viewport, double x)
	{
		return (x + viewport.getPositionX()) * viewport.getZoom();
	}

	public static double getViewY(Viewport viewport, double y)
	{
		return (y + viewport.getPositionY()) * viewport.getZoom();
	}

	public static void zoomFixed(Viewport viewport, Coordinate point,
			boolean in, double zoomStep)
	{
		// (x, y) that we want to keep fixed at the screen point (x, y)
		double frx = getRealX(viewport, point.getX());
		double fry = getRealY(viewport, point.getY());

		if (in) {
			viewport.setZoom(viewport.getZoom() * (1 + zoomStep));
		} else {
			viewport.setZoom(viewport.getZoom() / (1 + zoomStep));
		}

		// (x, y) of the (lon, lat) after applying the zoom change
		double fx = getViewX(viewport, frx);
		double fy = getViewY(viewport, fry);

		// shift the map to keep the (lon, lat) fixed
		double dx = fx - point.getX();
		double dy = fy - point.getY();

		viewport.setPositionX(
				viewport.getPositionX() - dx / viewport.getZoom());
		viewport.setPositionY(
				viewport.getPositionY() - dy / viewport.getZoom());
	}

}
