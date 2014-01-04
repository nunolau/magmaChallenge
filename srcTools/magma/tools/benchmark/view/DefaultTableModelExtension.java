/* Copyright 2009 Hochschule Offenburg
 * Klaus Dorer, Mathias Ehret, Stefan Glaser, Thomas Huber,
 * Simon Raffeiner, Srinivasa Ragavan, Thomas Rinklin,
 * Joachim Schilling, Rajit Shahi
 *
 * This file is part of magmaOffenburg.
 *
 * magmaOffenburg is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * magmaOffenburg is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with magmaOffenburg. If not, see <http://www.gnu.org/licenses/>.
 */

package magma.tools.benchmark.view;

import java.util.Collections;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import magma.tools.benchmark.model.TeamConfiguration;

/**
 * 
 * @author kdorer
 */
class DefaultTableModelExtension extends DefaultTableModel
{
	private static final long serialVersionUID = 1L;

	private Class[] columnTypes;

	private boolean[] columnEditables;

	public static DefaultTableModelExtension getInstance(
			List<TeamConfiguration> config)
	{
		final int COLUMNS = 7;

		if (config == null) {
			TeamConfiguration singleTeam = new TeamConfiguration("magma",
					"/host/Data/Projekte/RoboCup/Konfigurationen/runChallenge/",
					"startPlayerRunning.sh");
			config = Collections.singletonList(singleTeam);
		}

		Object[][] content = new Object[config.size()][COLUMNS];
		int i = 0;
		for (TeamConfiguration team : config) {
			content[i][0] = team.getName();
			content[i][1] = null;
			content[i][2] = null;
			content[i][3] = null;
			content[i][4] = null;
			content[i][5] = team.getPath();
			content[i][6] = team.getLaunch();
			i++;
		}

		String[] headers = new String[] { "team", "score", "falls", "speed",
				"off ground", "path", "binary" };

		return new DefaultTableModelExtension(content, headers);
	}

	/**
	 * @param data
	 * @param columnNames
	 */
	private DefaultTableModelExtension(Object[][] data, Object[] columnNames)
	{
		super(data, columnNames);

		columnTypes = new Class[] { String.class, Float.class, Integer.class,
				Float.class, Float.class, String.class, String.class };

		columnEditables = new boolean[] { true, false, false, false, false, true,
				true };
	}

	@Override
	public Class getColumnClass(int columnIndex)
	{
		return columnTypes[columnIndex];
	}

	@Override
	public boolean isCellEditable(int row, int column)
	{
		return columnEditables[column];
	}
}
