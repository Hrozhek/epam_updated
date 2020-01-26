package hometask18.reporting;

import hometask18.common.business.exception.checked.ReportException;

@FunctionalInterface
public interface ReportService {
    void exportData() throws ReportException;
}
