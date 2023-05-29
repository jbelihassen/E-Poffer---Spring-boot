import { Injectable } from '@angular/core';
import { Project } from './project';
import { FileHandel } from './imagesFile';
import { DomSanitizer } from '@angular/platform-browser';

@Injectable({
  providedIn: 'root'
})
export class ImageProcessingService {

  constructor(private sanitizer: DomSanitizer) { }
  public createImages(project: Project) {
    const projectImages: any[] = project.projectImages;

    const projectImagesToFileHandle: FileHandel[] = [];

    for (let i = 0; i < projectImages.length; i++) {
      const imageFileData = projectImages[i];

      const imageBlob = this.dataURItoBlob(imageFileData.picbyte, imageFileData.type);

      const imageFile = new File([imageBlob], imageFileData.name, { type: imageFileData.type });

      const finalFileHandle :FileHandel = {
        file: imageFile,
        url: this.sanitizer.bypassSecurityTrustUrl(window.URL.createObjectURL(imageFile))
      };

      projectImagesToFileHandle.push(finalFileHandle);
    }

    project.projectImages = projectImagesToFileHandle;
   return project;

  }
  public dataURItoBlob(picBytes: any, imageType: any) {
    const byteString = window.atob(picBytes);
    const arrayBuffer = new ArrayBuffer(byteString.length);
    const int8Array = new Uint8Array(arrayBuffer);

    for(let i = 0; i < byteString.length; i++) {
      int8Array[i] = byteString.charCodeAt(i);
    }

    const blob = new Blob([int8Array], { type: imageType});
    return blob;
  }

}


