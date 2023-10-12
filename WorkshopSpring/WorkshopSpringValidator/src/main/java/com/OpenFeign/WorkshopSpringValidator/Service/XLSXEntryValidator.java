package com.OpenFeign.WorkshopSpringValidator.Service;

import com.OpenFeign.WorkshopSpringValidator.Model.XLSXEntry;
import org.springframework.stereotype.Component;

@Component
public class XLSXEntryValidator {
    public boolean validateXLSXEntry(XLSXEntry xlsxEntry) {
        return validateInjuryLocation(xlsxEntry.getInjuryLocation()) && validateReportType(xlsxEntry.getReportType());
    }

    private boolean validateInjuryLocation(String injuryLocation) {
        return !injuryLocation.equalsIgnoreCase("N/A");
    }

    private boolean validateReportType(String reportType) {
        String[] allowedReportTypes = {"Near Miss", "Lost Time", "First Aid"};
        for (String allowedType : allowedReportTypes) {
            if (reportType.equalsIgnoreCase(allowedType)) {
                return true;
            }
        }
        return false;
    }
}
