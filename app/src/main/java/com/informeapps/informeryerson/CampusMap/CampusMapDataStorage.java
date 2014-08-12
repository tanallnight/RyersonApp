package com.informeapps.informeryerson.CampusMap;

/**
 * Created by Tanmay on 2014-08-11.
 */
public class CampusMapDataStorage {

    private String[] locationsArray = {
            "Architecture Building (ARC)",
            "Campus Planning and Facilities (BON)",
            "Campus Store (BKS)",
            "Centre for Urban Energy (CUE)",
            "Civil Engineering Building (MON)",
            "Co-operative Education (COP)",
            "Eric Palin Hall (EPH)",
            "George Vari Engineering and Computing Centre (ENG)",
            "Heaslip House (CED)",
            "Heidelberg Centre-School of Graphics Communications Management (HEI)",
            "International Living/Learning Centre (ILC)",
            "Jorgenson Hall (JOR)",
            "Kerr Hall East (KHE)",
            "Kerr Hall North (KHN)",
            "Kerr Hall South (KHS)",
            "Kerr Hall West (KHW)",
            "Library Building (LIB)",
            "Mattamy Athletic Centre (MAC)",
            "Oakham House (OAK)",
            "Office of University Advancement; University Scheduling (YNG)",
            "O'Keefe House (OKF)",
            "Pitman Hall (PIT)",
            "Parking Garage (PKG)",
            "Podium (POD)",
            "Projects Office (PRO)",
            "Recreation and Athletics Centre (RAC)",
            "Research and Graduate Studies (GER)",
            "Rogers Communications Centre (RCC)",
            "Ryerson Image Centre (RIC)",
            "Sally Horsfall Eaton Centre for Studies in Community Health (SHE)",
            "School of Image Arts (IMA)",
            "School of Interior Design (SID)",
            "South Bond Building (SBB)",
            "Student Campus Centre (SCC)",
            "Theatre School (THR)",
            "Ted Rogers School of Management (TRS)",
            "Victoria Building (VIC)",
            "Yonge-Dundas I (YDI)",
            "Yonge-Dundas Square (DSQ)"
    };

    private double[] locationLatArray = {
            43.659229
    };

    private double[] locationLongArray = {
            -79.377927
    };

    public String[] getLocationsArray() {
        return locationsArray;
    }

    public double[] getLocationLatArray() {
        return locationLatArray;
    }

    public double[] getLocationLongArray() {
        return locationLongArray;
    }
}
