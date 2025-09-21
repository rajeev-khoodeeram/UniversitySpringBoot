import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FacultyListComponent } from "@/components/faculty-list-component/faculty-list-component";


@Component({
    selector: 'features-widget',
    standalone: true,
    imports: [CommonModule, FacultyListComponent],
    template: ` <div id="features" class="py-3 px-3 lg:px-20 mt-8 mx-0 lg:mx-20">
        <div class="grid grid-cols-3 gap-4 justify-center">
            <div class="col-span-12 text-center mt-20 mb-6">
                <div class="text-surface-900 dark:text-surface-0 font-normal mb-2 text-4xl">Browse all faculties</div>
                <span class="text-muted-color text-2xl">Select your future faculty...</span>
            </div>

           <!-- Add list of faculties here -->
            <app-faculty-list-component></app-faculty-list-component>
            <div
                class="col-span-12 mt-20 mb-20 p-2 md:p-20"
                style="border-radius: 20px; background: linear-gradient(0deg, rgba(255, 255, 255, 0.6), rgba(255, 255, 255, 0.6)), radial-gradient(77.36% 256.97% at 77.36% 57.52%, #efe1af 0%, #c3dcfa 100%)"
            >
                <div class="flex flex-col justify-center items-center text-center px-4 py-4 md:py-0">
                    <div class="text-gray-900 mb-2 text-3xl font-semibold">Joséphine Miller</div>
                    <span class="text-gray-600 text-2xl">Peak Interactive</span>
                    <p class="text-gray-900 sm:line-height-2 md:line-height-4 text-2xl mt-6" style="max-width: 800px">
                        “Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.”
                    </p>
                    <img src="https://primefaces.org/cdn/templates/sakai/landing/peak-logo.svg" class="mt-6" alt="Company logo" />
                </div>
            </div>
        </div>
    </div>`
})
export class FeaturesWidget {}
