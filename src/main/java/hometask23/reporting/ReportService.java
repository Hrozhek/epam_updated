package hometask23.reporting;

import hometask23.common.business.exception.checked.ReportException;

@FunctionalInterface
public interface ReportService {
    void exportData() throws ReportException;
}
