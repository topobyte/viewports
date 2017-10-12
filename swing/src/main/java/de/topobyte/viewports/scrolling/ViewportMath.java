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

import javax.swing.JComponent;

import de.topobyte.viewports.scrolling.HasMargin;
import de.topobyte.viewports.scrolling.HasScene;
import de.topobyte.viewports.scrolling.Viewport;

public class ViewportMath<T extends JComponent & Viewport & HasScene & HasMargin>
{

	private T view;

	public ViewportMath(T view)
	{
		this.view = view;
	}

	/*
	 * Viewport translation offsets
	 */

	public double getMinimumOffset(boolean horizontal)
	{
		if (horizontal) {
			return 0 - view.getScene().getWidth() - view.getMargin()
					+ view.getWidth() / view.getZoom();
		} else {
			return 0 - view.getScene().getHeight() - view.getMargin()
					+ view.getHeight() / view.getZoom();
		}
	}

	public double getMaximumOffset()
	{
		return view.getMargin();
	}

	/*
	 * Methods for the BoundedRangeModel
	 */

	public int getRangeMinimum()
	{
		return (int) Math.round(0 - view.getMargin() * view.getZoom());
	}

	public int getRangeMaximum(boolean horizontal)
	{
		if (horizontal) {
			return (int) Math.round((view.getScene().getWidth() + view
					.getMargin()) * view.getZoom());
		} else {
			return (int) Math.round((view.getScene().getHeight() + view
					.getMargin()) * view.getZoom());
		}
	}

	public int getRangeExtent(boolean horizontal)
	{
		if (horizontal) {
			return (int) Math.round(view.getWidth());
		} else {
			return (int) Math.round(view.getHeight());
		}
	}

	/*
	 * Compute the range value for the current viewport offset
	 */
	public int getRangeValue(boolean horizontal)
	{
		if (horizontal) {
			return (int) Math.round(-view.getPositionX() * view.getZoom());
		} else {
			return (int) Math.round(-view.getPositionY() * view.getZoom());
		}
	}

	/*
	 * Compute viewport offset corresponding to a range value
	 */
	public double getViewportOffset(int rangeValue, boolean horizontal)
	{
		rangeValue = Math.min(rangeValue, getRangeMaximum(horizontal)
				- getRangeExtent(horizontal));
		rangeValue = Math.max(rangeValue, getRangeMinimum());
		return -rangeValue / view.getZoom();
	}

}
