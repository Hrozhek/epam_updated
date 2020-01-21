package hometask15.reporting;

import hometask15.common.business.exception.checked.ReportException;

public interface ReportService {
    void exportData() throws ReportException;
}
