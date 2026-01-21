package com.emi.dto.requestDto;



import java.math.BigDecimal;
import java.time.LocalDateTime;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import lombok.NoArgsConstructor;

@XmlRootElement
@Data
@NoArgsConstructor
@Schema(description="Request Bookcontent DTO")
public class RequestBookContentDto {

	       @Schema(
		        example = "7635",
		        description = "ID of the book to which this content belongs",
		        required = true
		    )
		    private Long bookId;

		    @Schema(
		        example = "5",
		        description = "Chapter number in the book",
		        required = true
		    )
		    private Integer chapterNumber;

		    @Schema(
		        example = "Introduction to Spring",
		        description = "Title of the chapter",
		        required = true
		    )
		    private String title;

		    @Schema(
		        description = "Actual content or markdown text of the chapter"
		    )
		    private String content;

		    @Schema(
		        example = "199.00",
		        description = "Price of this content"
		    )
		    private BigDecimal price;

		    @Schema(
		        example = "true",
		        description = "Whether the content is saved as draft"
		    )
		    private Boolean draft;
		
}
