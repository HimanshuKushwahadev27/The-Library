package com.emi.dto.requestDto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@XmlRootElement
@Schema(description="Author Request DTO")
//user gives these information
public class RequestAuthordto {
	
	@Schema(example="theWonder" , description="NickName of the Author")
	@NotBlank(message="Please enter the nickname")
	private String penName;
	
	@Schema(example="I am an avid reader" , description="More info on the user")
	@Size(max=2000)
	private String bio;

	@Schema(example="www.img.com" , description="url to profile pic")
	private String url;
}
