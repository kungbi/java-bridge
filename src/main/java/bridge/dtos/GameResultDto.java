package bridge.dtos;

public record GameResultDto(MapStateDto mapState, boolean isSuccess, int tryCount) {

    public String getGameResult() {
        if (isSuccess) {
            return "성공";
        }
        return "실패";
    }
}
