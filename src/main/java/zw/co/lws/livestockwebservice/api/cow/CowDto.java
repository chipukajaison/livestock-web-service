package zw.co.lws.livestockwebservice.api.cow;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import zw.co.lws.livestockwebservice.domain.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CowDto {
    private String tagNumber;
    private LocalDate dateOfBirth;
    private Type type;
    private Category category;
    private Gender gender;
    private String ownerName;

    public static CowDto fromCowDto(Cow cow){
        CowDto cowDto = new CowDto();
        cowDto.setDateOfBirth(cow.getDateOfBirth());
        cowDto.setGender(cow.getGender());
        cowDto.setType(cow.getType());
        cowDto.setTagNumber(cow.getTagNumber());
        cowDto.setCategory(cow.getCategory());
        cowDto.setOwnerName(cow.getOwner().getFullName());
        return cowDto;
    }
}