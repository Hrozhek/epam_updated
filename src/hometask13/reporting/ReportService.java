package hometask13.reporting;

import hometask13.common.business.exception.checked.ReportException;

public interface ReportService {
    void exportData() throws ReportException;
}
