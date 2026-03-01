public abstract class Exporter {

    public final ExportResult export(ExportRequest req) {
        validate(req);
        return doExport(req);
    }

    private void validate(ExportRequest req) {
        if (req == null) {
            throw new IllegalArgumentException("Request cannot be null");
        }
        if (req.title == null) {
            throw new IllegalArgumentException("Title cannot be null");
        }
    }

    protected abstract ExportResult doExport(ExportRequest req);  
    //only subclass can call this method
}