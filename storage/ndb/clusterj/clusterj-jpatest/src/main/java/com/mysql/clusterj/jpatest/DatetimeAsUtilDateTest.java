/*
   Copyright 2010 Sun Microsystems, Inc.
   All rights reserved. Use is subject to license terms.

   This program is free software; you can redistribute it and/or modify
   it under the terms of the GNU General Public License as published by
   the Free Software Foundation; version 2 of the License.

   This program is distributed in the hope that it will be useful,
   but WITHOUT ANY WARRANTY; without even the implied warranty of
   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   GNU General Public License for more details.

   You should have received a copy of the GNU General Public License
   along with this program; if not, write to the Free Software
   Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301  USA
*/

package com.myblockchain.clusterj.jpatest;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import com.myblockchain.clusterj.jpatest.model.DatetimeAsUtilDateTypes;
import com.myblockchain.clusterj.jpatest.model.IdBase;

/** Test that Datetimes can be read and written. 
 * case 1: Write using JDBC, read using NDB.
 * case 2: Write using NDB, read using JDBC.
 * Schema
 *
drop table if exists datetimetypes;
create table datetimetypes (
 id int not null primary key,

 datetime_null_hash datetime,
 datetime_null_btree datetime,
 datetime_null_both datetime,
 datetime_null_none datetime,

 datetime_not_null_hash datetime,
 datetime_not_null_btree datetime,
 datetime_not_null_both datetime,
 datetime_not_null_none datetime

) ENGINE=ndbcluster DEFAULT CHARSET=latin1;

create unique index idx_datetime_null_hash using hash on datetimetypes(datetime_null_hash);
create index idx_datetime_null_btree on datetimetypes(datetime_null_btree);
create unique index idx_datetime_null_both on datetimetypes(datetime_null_both);

create unique index idx_datetime_not_null_hash using hash on datetimetypes(datetime_not_null_hash);
create index idx_datetime_not_null_btree on datetimetypes(datetime_not_null_btree);
create unique index idx_datetime_not_null_both on datetimetypes(datetime_not_null_both);
 */
public class DatetimeAsUtilDateTest extends AbstractJPABaseTest {

    static int NUMBER_OF_INSTANCES = 10;

    @Override
    protected boolean getDebug() {
        return false;
    }

    @Override
    protected int getNumberOfInstances() {
        return NUMBER_OF_INSTANCES;
    }

    @Override
    protected String getTableName() {
        return "datetimetypes";
    }

    /** Subclasses override this method to provide the model class for the test */
    @Override
    protected Class<? extends IdBase> getModelClass() {
        return DatetimeAsUtilDateTypes.class;
    }

    /** Subclasses override this method to provide values for rows (i) and columns (j) */
    @Override
    protected Object getColumnValue(int i, int j) {
        return new Date(getMillisFor(1980, 0, i + 1, 0, 0, j));
    }

    @Override
    /** Subclasses must override this method to implement the model factory for the test */
    protected IdBase getNewInstance(Class<? extends IdBase> modelClass) {
        return new DatetimeAsUtilDateTypes();
    }

    public void testWriteJDBCReadJPA() {
        writeJDBCreadJPA();
        failOnError();
    }

    public void testWriteJPAReadJDBC() {
        writeJPAreadJDBC();
        failOnError();
   }

    public void testWriteJDBCReadJDBC() {
        writeJDBCreadJDBC();
        failOnError();
    }

    public void testWriteJPAReadJPA() {
        writeJPAreadJPA();
        failOnError();
   }

   static ColumnDescriptor not_null_hash = new ColumnDescriptor
            ("datetime_not_null_hash", new InstanceHandler() {
        public void setFieldValue(IdBase instance, Object value) {
            ((DatetimeAsUtilDateTypes)instance).setDatetime_not_null_hash((Date)value);
        }
        public Object getFieldValue(IdBase instance) {
            return ((DatetimeAsUtilDateTypes)instance).getDatetime_not_null_hash();
        }
        public void setPreparedStatementValue(PreparedStatement preparedStatement, int j, Object value)
                throws SQLException {
            Timestamp timestamp = new Timestamp(((Date)value).getTime());
            preparedStatement.setTimestamp(j, timestamp);
        }
        public Object getResultSetValue(ResultSet rs, int j) throws SQLException {
            return rs.getTimestamp(j);
        }
    });

    static ColumnDescriptor not_null_btree = new ColumnDescriptor
            ("datetime_not_null_btree", new InstanceHandler() {
        public void setFieldValue(IdBase instance, Object value) {
            ((DatetimeAsUtilDateTypes)instance).setDatetime_not_null_btree((Date)value);
        }
        public Object getFieldValue(IdBase instance) {
            return ((DatetimeAsUtilDateTypes)instance).getDatetime_not_null_btree();
        }
        public void setPreparedStatementValue(PreparedStatement preparedStatement, int j, Object value)
                throws SQLException {
            Timestamp timestamp = new Timestamp(((Date)value).getTime());
            preparedStatement.setTimestamp(j, timestamp);
        }
        public Object getResultSetValue(ResultSet rs, int j) throws SQLException {
            return rs.getTimestamp(j);
        }
    });

    static ColumnDescriptor not_null_both = new ColumnDescriptor
            ("datetime_not_null_both", new InstanceHandler() {
        public void setFieldValue(IdBase instance, Object value) {
            ((DatetimeAsUtilDateTypes)instance).setDatetime_not_null_both((Date)value);
        }
        public Date getFieldValue(IdBase instance) {
            return ((DatetimeAsUtilDateTypes)instance).getDatetime_not_null_both();
        }
        public void setPreparedStatementValue(PreparedStatement preparedStatement, int j, Object value)
                throws SQLException {
            Timestamp timestamp = new Timestamp(((Date)value).getTime());
            preparedStatement.setTimestamp(j, timestamp);
        }
        public Object getResultSetValue(ResultSet rs, int j) throws SQLException {
            return rs.getTimestamp(j);
        }
    });

    static ColumnDescriptor not_null_none = new ColumnDescriptor
            ("datetime_not_null_none", new InstanceHandler() {
        public void setFieldValue(IdBase instance, Object value) {
            ((DatetimeAsUtilDateTypes)instance).setDatetime_not_null_none((Date)value);
        }
        public Date getFieldValue(IdBase instance) {
            return ((DatetimeAsUtilDateTypes)instance).getDatetime_not_null_none();
        }
        public void setPreparedStatementValue(PreparedStatement preparedStatement, int j, Object value)
                throws SQLException {
            Timestamp timestamp = new Timestamp(((Date)value).getTime());
            preparedStatement.setTimestamp(j, timestamp);
        }
        public Object getResultSetValue(ResultSet rs, int j) throws SQLException {
            return rs.getTimestamp(j);
        }
    });

    protected static ColumnDescriptor[] columnDescriptors = new ColumnDescriptor[] {
            not_null_hash,
            not_null_btree,
            not_null_both,
            not_null_none
        };

    @Override
    protected ColumnDescriptor[] getColumnDescriptors() {
        return columnDescriptors;
    }

}
