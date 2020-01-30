package hometask20.reporting;

import hometask20.common.business.exception.checked.ReportException;

@FunctionalInterface
public interface ReportService {
    void exportData() throws ReportException;
}
