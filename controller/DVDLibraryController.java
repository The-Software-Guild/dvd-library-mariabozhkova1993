package controller;

import dao.DVDLibraryDao;
import dao.DVDLibraryDaoException;
import dao.DVDLibraryDaoFileImpl;
import dto.DVD;
import ui.DVDLibraryView;
import ui.UserIO;
import ui.UserIOConsoleImpl;

import java.util.List;

public class DVDLibraryController {

    private UserIO user_input = new UserIOConsoleImpl();
    private DVDLibraryDao dao = new DVDLibraryDaoFileImpl();
    private DVDLibraryView view = new DVDLibraryView();

    public void run() {
        boolean keepGoing = true;
        int menuSelection;

        try {
            while (keepGoing) {

                menuSelection = getMenuSelection();

                switch (menuSelection) {

                    case 1:
                        listDVDs();
                        break;

                    case 2:
                        createDVD();
                        break;

                    case 3:
                        getDVD();
                        break;

                    case 4:
                        searchDVD();
                    case 5:
                        editDVD();
                        break;

                    case 6:
                        removeDVD();
                        break;

                    case 7:
                        exitMessage();
                        break;

                    default:
                        unknownCommand();
                }
            }

            exitMessage();

        } catch (DVDLibraryDaoException e) {

            view.displayErrorMessage(e.getMessage());
        }
    }

    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }

    private void createDVD() throws DVDLibraryDaoException {
        DVD dvd = view.getNewDVDInfo();
        dao.addDVD(dvd.getDvdId(), dvd);

        view.displayCreateDVDBanner();
        view.displayCreateDVDSuccessBanner();
    }

    private void getDVD() throws DVDLibraryDaoException {
        String dvdId = view.getDVDIdChoice();
        DVD dvd = dao.getDVD(dvdId);

        view.displayDVDBanner();
        view.displayDVD(dvd);
    }

    private void listDVDs() throws DVDLibraryDaoException {
        List<DVD> dvdList = dao.getAllDVDs();

        view.displayAllDVDBanner();
        view.displayAllDVDs(dvdList);
    }

    private void searchDVD() throws DVDLibraryDaoException {
        String title = view.getDVDTitleChoice();

        view.displayFindDVDbyTitleBanner(title);

        List<DVD> filteredDVDs = dao.findDVD(title);

        view.displayAllDVDs(filteredDVDs);
    }

    private void removeDVD() throws DVDLibraryDaoException {
        String dvdId = view.getDVDIdChoice();
        DVD dvd = dao.removeDVD(dvdId);

        view.displayRemoveDVD(dvd);
    }

    private void editDVD() throws DVDLibraryDaoException {
        view.displayEditDVDBanner();

        String dvdId = view.getDVDIdChoice();
        DVD dvd = dao.getDVD(dvdId);

        if (dvd != null) {

            int editMenuSelection;
            boolean keepGoing = true;

            view.displayDVD(dvd);

            while (keepGoing) {

                editMenuSelection = view.printEditMenuAndGetSelection();

                switch (editMenuSelection) {
                    case 1:
                        editDVDTitle(dvdId);
                        break;

                    case 2:
                        editDVDReleaseDate(dvdId);
                        break;

                    case 3:
                        editDVDMpaaRating(dvdId);
                        break;

                    case 4:
                        editDVDDirectorName(dvdId);
                        break;

                    case 5:
                        editDVDStudio(dvdId);
                        break;

                    case 6:
                        editDVDUserRating(dvdId);
                        break;

                    case 7:
                        keepGoing = false;
                        break;

                    default:
                        unknownCommand();
                }


                if (keepGoing = false) {
                    break;
                }
            }

        } else {
            view.displayNullDVDSelection();
        }
    }

    public void editDVDTitle(String dvdId) throws DVDLibraryDaoException {
        view.displayEditDVDField("Title");

        String newValue = view.getNewDVDFieldValue("Release Date");
        DVD editedDVD = dao.editDVDTitle(dvdId, newValue);

        view.displayEditSuccess();
    }

    public void editDVDReleaseDate(String dvdId) throws DVDLibraryDaoException {
        view.displayEditDVDField("Release Date");

        String newValue = view.getNewDVDFieldValue("Release Date");
        DVD editedDVD = dao.editDVDReleaseDate(dvdId, newValue);

        view.displayEditSuccess();
    }

    public void editDVDMpaaRating(String dvdId) throws DVDLibraryDaoException {
        view.displayEditDVDField("MPAA Rating");

        String newValue = view.getNewDVDFieldValue("Release Date");
        DVD editedDVD = dao.editDVDMpaaRating(dvdId, newValue);

        view.displayEditSuccess();
    }

    public void editDVDDirectorName(String dvdId) throws DVDLibraryDaoException {
        view.displayEditDVDField("Director Name");

        String newValue = view.getNewDVDFieldValue("Release Date");
        DVD editedDVD = dao.editDVDDirectorName(dvdId, newValue);

        view.displayEditSuccess();
    }

    public void editDVDStudio(String dvdId) throws DVDLibraryDaoException {
        view.displayEditDVDField("Studio");

        String newValue = view.getNewDVDFieldValue("Release Date");
        DVD editedDVD = dao.editDVDStudio(dvdId, newValue);

        view.displayEditSuccess();
    }

    public void editDVDUserRating(String dvdId) throws DVDLibraryDaoException {
        view.displayEditDVDField("User Rating");

        String newValue = view.getNewDVDFieldValue("Release Date");
        DVD editedDVD = dao.editDVDUserRating(dvdId, newValue);

        view.displayEditSuccess();
    }

    private void exitMessage(){
        view.displayExitBanner();
    }
    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

}