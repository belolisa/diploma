package com.emc.ga4gh.DAO.orient.object;

import com.emc.ga4gh.DAO.ReadDAO;
import com.emc.ga4gh.DAO.builder.SelectBuilder;
import com.emc.ga4gh.DAO.builder.SelectBuilderImpl;
import com.emc.ga4gh.DTO.Read;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by liza on 16.04.15.
 */

@Repository
public class ReadObjectDAO extends AbstractObjectDAO<Read> implements ReadDAO {

    @Override
    protected String getCollectionName() {
        return "Read";
    }


    @Override
    public List<Read> findIncOrdered(String referenceId, String referenceName, Long start, Long end, List<String> readGroupIds) {
        SelectBuilder builder = getSelectBuilder(referenceId, referenceName, start, end, readGroupIds);

        return querySelect(builder);
    }

    @Override
    public List<Read> findIncOrderedPage(String referenceId, String referenceName, Long start, Long end, List<String> readGroupIds, Integer pageSize, Integer pageNumber) {
        SelectBuilder builder = getSelectBuilder(referenceId, referenceName, start, end, readGroupIds);
        if (pageNumber != null) {
            builder.setQueryParameter("limit", String.valueOf(pageSize));
            builder.setQueryParameter("skip", String.valueOf(pageSize * pageNumber));
        } else {
            // TODO: or not?...
        }
        return querySelect(builder);
    }

    private SelectBuilder getSelectBuilder(String referenceId, String referenceName, Long start, Long end, List<String> readGroupIds) {
        SelectBuilder builder = (SelectBuilder) new SelectBuilderImpl("Read")
                .setObjectParameterInList("readGroupId", readGroupIds)
                .setObjectParameter("alignmentStart", ">=", String.valueOf(start));
        if (end != null) {
            builder.setObjectParameter("alignmentEnd", "<=", String.valueOf(end))
                    .setObjectParameter("alignmentEnd", ">=", String.valueOf(start))
                    .setObjectParameter("alignmentStart", "<=", String.valueOf(end));
        }
        if (referenceId != null) {
            builder.setObjectParameterEquals("referenceId", referenceId);
        }
        if (referenceName != null) {
            builder.setObjectParameterEquals("referenceName", referenceName);
        }
        builder.setOrder("path ASC, numberInFile ASC");
        return builder;
    }
}
