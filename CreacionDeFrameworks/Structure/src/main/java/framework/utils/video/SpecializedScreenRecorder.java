package framework.utils.video;

import framework.base.FrameworkException;
import org.monte.media.Format;
import org.monte.media.Registry;
import org.monte.screenrecorder.ScreenRecorder;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SpecializedScreenRecorder extends ScreenRecorder {
    private String name;

    public SpecializedScreenRecorder(GraphicsConfiguration cfg, Rectangle captureArea, Format fileFormat,
                                     Format screenFormat, Format mouseFormat, Format audioFormat, File movieFile,
                                     String name) throws IOException, AWTException {
        super(cfg, captureArea, fileFormat, screenFormat, mouseFormat, audioFormat, movieFile);
        this.name = name;

    }

    @Override
    protected  File createMovieFile(Format fileFormat) throws IOException {
        if (!movieFolder.exists()){
            movieFolder.mkdirs();
        } else if (!movieFolder.isDirectory()){
            new FrameworkException(String.format("\\%s\\ is not a directory", movieFolder));
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH");
        return  new File(movieFolder, name +"-"+dateFormat.format(new Date())+"."+ Registry.getInstance().getExtension(fileFormat));
    }
}
