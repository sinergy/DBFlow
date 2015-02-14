package com.raizlabs.android.dbflow.sql.migration;

import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import com.raizlabs.android.dbflow.sql.index.Index;
import com.raizlabs.android.dbflow.structure.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Description: Defines and enables an Index structurally through a migration.
 */
public class IndexMigration<ModelClass extends Model> extends BaseMigration {

    /**
     * The list of columns to add
     */
    private List<String> mColumns = new ArrayList<>();

    /**
     * The table to index on
     */
    private Class<ModelClass> mOnTable;

    /**
     * The name of this index
     */
    private String mName;

    public IndexMigration(@NonNull String name, @NonNull Class<ModelClass> mOnTable) {
        this.mOnTable = mOnTable;
        this.mName = name;
    }

    @Override
    public void migrate(SQLiteDatabase database) {
        Index<ModelClass> index = new Index<ModelClass>(mName)
                .on(mOnTable, mColumns.toArray(new String[mColumns.size()]));
        index.enable();
    }

    /**
     * Adds a column to the underlying INDEX
     *
     * @param columnName The name of the column to add to the Index
     * @return This migration
     */
    public IndexMigration addColumn(String columnName) {
        if (!mColumns.contains(columnName)) {
            mColumns.add(columnName);
        }
        return this;
    }

}