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

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.topobyte.awt.util.GraphicsUtil;
import de.topobyte.lina.Matrix;
import de.topobyte.viewports.BaseScenePanel;
import de.topobyte.viewports.geometry.Coordinate;
import de.topobyte.viewports.geometry.CoordinateTransformer;
import de.topobyte.viewports.geometry.Rectangle;
import de.topobyte.viewports.scrolling.HasMargin;
import de.topobyte.viewports.scrolling.HasScene;
import de.topobyte.viewports.scrolling.TransformHelper;
import de.topobyte.viewports.scrolling.ViewportWithSignals;

public class ScenePanel extends BaseScenePanel
		implements ViewportWithSignals, HasScene, HasMargin
{

	private static final long serialVersionUID = 4888387704552849742L;

	final static Logger logger = LoggerFactory.getLogger(ScenePanel.class);

	public ScenePanel(Rectangle scene)
	{
		super(scene);
	}

	private CoordinateTransformer transformer = null;

	@Override
	public void paint(Graphics graphics)
	{
		super.paint(graphics);
		Graphics2D g = (Graphics2D) graphics;
		GraphicsUtil.useAntialiasing(g, true);

		Matrix matrix = TransformHelper.createMatrix(scene, this);
		transformer = new CoordinateTransformer(matrix);

		g.setColor(Color.WHITE);
		fillRect(g, scene.getX1(), scene.getY1(), scene.getX2(), scene.getY2());

		g.setColor(Color.BLACK);
		drawLine(g, 10, 10, 100, 100);
	}

	private void fillRect(Graphics2D g, double x1, double y1, double x2,
			double y2)
	{
		Coordinate start = new Coordinate(x1, y1);
		Coordinate end = new Coordinate(x2, y2);
		Coordinate tStart = transformer.transform(start);
		Coordinate tEnd = transformer.transform(end);

		Rectangle2D rectangle = new Rectangle2D.Double(tStart.getX(),
				tStart.getY(), tEnd.getX() - tStart.getX(),
				tEnd.getY() - tStart.getY());
		g.fill(rectangle);
	}

	private void drawLine(Graphics2D g, double x1, double y1, double x2,
			double y2)
	{
		Coordinate start = new Coordinate(x1, y1);
		Coordinate end = new Coordinate(x2, y2);
		Coordinate tStart = transformer.transform(start);
		Coordinate tEnd = transformer.transform(end);

		Line2D line = new Line2D.Double(tStart.getX(), tStart.getY(),
				tEnd.getX(), tEnd.getY());
		g.draw(line);
	}

}
