package demo.annotation;

@CurseInfoAnnotation(curseName = "java", curseTag = "ba", curseProfile = "zhh", curseIndex = 100)
public class ImoockCurse {
    @PersonInfoAnnotation(name="zp", language = {"java","js"})
    private String author;

    @CurseInfoAnnotation(curseName = "java", curseTag = "ba", curseProfile = "zhh", curseIndex = 100)
    public void getCurseInfo() {

    }
}
