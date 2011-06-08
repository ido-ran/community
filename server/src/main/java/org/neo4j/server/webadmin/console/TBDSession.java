/**
 * Copyright (c) 2002-2011 "Neo Technology,"
 * Network Engine for Objects in Lund AB [http://neotechnology.com]
 *
 * This file is part of Neo4j.
 *
 * Neo4j is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
/**
* Copyright (c) 2002-2011 "Neo Technology,"
* Network Engine for Objects in Lund AB [http://neotechnology.com]
*
* This file is part of Neo4j.
*
* Neo4j is free software: you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.
*
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
package org.neo4j.server.webadmin.console;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.tbd.ExecutionEngine;
import org.neo4j.tbd.ExecutionResult;
import org.neo4j.tbd.TBDParser;
import org.neo4j.tbd.SyntaxError;
import org.neo4j.tbd.commands.Query;

/**
* Created by Andres Taylor
* Date: 5/30/11
* Time: 21:54
*/
public class TBDSession implements ScriptSession
{
    private GraphDatabaseService graph;
    private final ExecutionEngine engine;
    private final TBDParser parser;

    public TBDSession(GraphDatabaseService graph)
    {
        this.graph = graph;
        parser = new TBDParser();
        engine = new ExecutionEngine(graph);
    }

    @Override
    public String evaluate(String script)
    {
        try
        {
            Query query = parser.parse(script);
            ExecutionResult result = engine.execute(query);

            return result.toString();
        } catch (SyntaxError error)
        {
            return error.getMessage();
        }
    }
}
