package com.emc.ga4gh.DAO.builder;

import java.util.Map;

/**
 * Created by liza on 16.04.15.
 */
public class SelectBuilderImpl extends AbstractSQLBuilder implements SelectBuilder {

    public SelectBuilderImpl(String from) {
        setQueryParameter("from", from);
    }

    @Override
    public SelectBuilder setSelect(String select) {
        setQueryParameter("select", select);
        return this;
    }

    @Override
    public SelectBuilder setOrder(String order) {
        setQueryParameter("order by", order);
        return this;
    }

    @Override
    public String build() {
        return "select " + (getQueryParameters().containsKey("select") ? getQueryParameters().get("select") : "") +
                (getQueryParameters().containsKey("from") ? "from" + " " + getQueryParameters().get("from") : "") +
                (!getObjectParameters().isEmpty() ? " where " + buildObjectParamString(): "") +
                (getQueryParameters().containsKey("skip") ? " " + "skip" + " " + getQueryParameters().get("skip") : "") +
                (getQueryParameters().containsKey("limit") ? " " + "limit" + " " + getQueryParameters().get("limit") : "") +
                (getQueryParameters().containsKey("order by") ? " " + "order by" + " " + getQueryParameters().get("order by") : "");
    }

    @Override
    protected String buildObjectParamString() {
        StringBuilder builder = new StringBuilder();
        String delimiter = "";
        for (Map.Entry<String, ParamStatement> entry : getObjectParameters().entrySet()) {
            builder.append(delimiter)
                    .append(entry.getKey())
                    .append(entry.getValue().getInfix())
                    .append(entry.getValue().getValue());
            delimiter = " AND ";
        }
        
        return builder.toString();
    }
}
