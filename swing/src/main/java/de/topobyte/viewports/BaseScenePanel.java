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

package de.topobyte.viewports;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.topobyte.viewports.geometry.Rectangle;
import de.topobyte.viewports.scrolling.HasMargin;
import de.topobyte.viewports.scrolling.HasScene;
import de.topobyte.viewports.scrolling.ViewportListener;
import de.topobyte.viewports.scrolling.ViewportWithSignals;

public class BaseScenePanel extends JPanel
		implements ViewportWithSignals, HasScene, HasMargin
{

	private static final long serialVersionUID = -1230821197563599750L;

	final static Logger logger = LoggerFactory.getLogger(BaseScenePanel.class);

	protected Rectangle scene;

	protected int margin = 150;

	protected double positionX = 0;
	protected double positionY = 0;
	protected double zoom = 1;

	public BaseScenePanel(Rectangle scene)
	{
		this.scene = scene;

		addComponentListener(new ComponentAdapter() {

			@Override
			public void componentResized(ComponentEvent e)
			{
				checkBounds();
			}

		});
	}

	@Override
	public Rectangle getScene()
	{
		return scene;
	}

	@Override
	public double getMargin()
	{
		return margin;
	}

	@Override
	public double getPositionX()
	{
		return positionX;
	}

	@Override
	public double getPositionY()
	{
		return positionY;
	}

	@Override
	public double getViewportWidth()
	{
		return getWidth();
	}

	@Override
	public double getViewportHeight()
	{
		return getHeight();
	}

	@Override
	public double getZoom()
	{
		return zoom;
	}

	protected void internalSetZoom(double value)
	{
		zoom = value;
	}

	protected void internalSetPositionX(double value)
	{
		positionX = value;
	}

	protected void internalSetPositionY(double value)
	{
		positionY = value;
	}

	@Override
	public void setPositionX(double value)
	{
		internalSetPositionX(value);
		fireViewportListenersViewportChanged();
	}

	@Override
	public void setPositionY(double value)
	{
		internalSetPositionY(value);
		fireViewportListenersViewportChanged();
	}

	@Override
	public void setZoom(double zoom)
	{
		setZoomCentered(zoom);
	}

	public void setZoomCentered(double zoom)
	{
		double mx = -positionX + getWidth() / this.zoom / 2.0;
		double my = -positionY + getHeight() / this.zoom / 2.0;

		internalSetZoom(zoom);
		internalSetPositionX(getWidth() / zoom / 2.0 - mx);
		internalSetPositionY(getHeight() / zoom / 2.0 - my);

		checkBounds();
		fireViewportListenersZoomChanged();
		repaint();
	}

	protected void checkBounds()
	{
		boolean update = false;
		if (-positionX + getWidth() / zoom > getScene().getWidth() + margin) {
			logger.debug("Moved out of viewport at right");
			internalSetPositionX(
					getWidth() / zoom - getScene().getWidth() - margin);
			update = true;
		}
		if (positionX > margin) {
			logger.debug("Scrolled too much to the left");
			internalSetPositionX(margin);
			update = true;
		}
		if (-positionY + getHeight() / zoom > getScene().getHeight() + margin) {
			logger.debug("Moved out of viewport at bottom");
			internalSetPositionY(
					getHeight() / zoom - getScene().getHeight() - margin);
			update = true;
		}
		if (positionY > margin) {
			logger.debug("Scrolled too much to the top");
			internalSetPositionY(margin);
			update = true;
		}
		if (update) {
			repaint();
		}
		fireViewportListenersViewportChanged();
	}

	private List<ViewportListener> viewportListeners = new ArrayList<>();

	@Override
	public void addViewportListener(ViewportListener listener)
	{
		viewportListeners.add(listener);
	}

	@Override
	public void removeViewportListener(ViewportListener listener)
	{
		viewportListeners.remove(listener);
	}

	protected void fireViewportListenersViewportChanged()
	{
		for (ViewportListener listener : viewportListeners) {
			listener.viewportChanged();
		}
	}

	protected void fireViewportListenersZoomChanged()
	{
		for (ViewportListener listener : viewportListeners) {
			listener.zoomChanged();
		}
	}

}
