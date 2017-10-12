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
import javax.swing.JFrame;

import de.topobyte.viewports.ScenePanel;
import de.topobyte.viewports.geometry.Rectangle;
import de.topobyte.viewports.scrolling.ScrollableView;

public class TestScenePanel
{

	public static void main(String[] args)
	{
		JFrame frame = new JFrame("Test");

		Rectangle scene = new Rectangle(0, 0, 500, 500);
		ScenePanel scenePanel = new ScenePanel(scene);

		ScrollableView<ScenePanel> scrollableView = new ScrollableView<>(
				scenePanel);

		frame.add(scrollableView);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 600);
		frame.setVisible(true);
	}

}
