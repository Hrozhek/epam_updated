package hometask14.reporting;

import hometask13.common.business.exception.checked.ReportException;

public interface ReportService {
    void exportData() throws ReportException;
}
