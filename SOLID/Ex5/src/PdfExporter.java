import java.nio.charset.StandardCharsets;

public class PdfExporter implements Exporter {
    private static final int MAX_BODY = 20;

    @Override
    public ExportResult export(ExportRequest req) {
        if (req == null) return ExportResult.error("application/pdf", "request is null");
        if (req.body != null && req.body.length() > MAX_BODY) {
            return ExportResult.error("application/pdf", "PDF cannot handle content > " + MAX_BODY + " chars");
        }
        String body = req.body == null ? "" : req.body;
        String title = req.title == null ? "" : req.title;
        String fakePdf = "PDF(" + title + "):" + body;
        return ExportResult.ok("application/pdf", fakePdf.getBytes(StandardCharsets.UTF_8));
    }
}
